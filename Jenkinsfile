#!groovy
@Library('shared@master') _

pipeline
{
    agent
    {
        label 'docker'
    }

    parameters
    {
      string(defaultValue: 'dev', description: 'Profile name to use. Default is dev', name: 'profileName')
    }

    environment
    {
        // CI = 'jenkins'
        openJDKBuildImage='openjdk:8-jdk-alpine'
        lambdaapigatewayImage = 'build/lambdaapigateway'
        // ecsRegistry = 'https://950377457506.dkr.ecr.us-east-1.amazonaws.com'
    }

    stages
    {
        stage('Setup')
        {
            steps
            {
                script {
                  echo "cleanup dir"
                  deleteDir()
                  checkout scm
                  echo "ecr Login"
                  sh 'eval $(aws --region us-east-1 ecr get-login)'
                }
                echo env.BRANCH_NAME
                echo "Gradle profile to use ---- ${params.profileName}"
            }
        }

        stage('Build JAR')
        {
            steps
            {
              gradleBuild "./gradlew build -x test"

            }
        }

        stage('Build Docker Image')
        {
            steps
            {
                script
                {
                    sh 'docker build -f Dockerfile -t ${lambdaapigatewayImage} .'

                }
            }
        }

        stage('Push Image to AWS ECS')
        {
            steps
            {
                script
                {
                    def rev = sh(
                      returnStdout: true,
                      script: 'git rev-parse --short HEAD').trim()

                      if (dockerTagExists(lambdaapigatewayImage, rev)){
                         echo "${rev} already exists. don't push or deploy."
                      }
                      else{
                        if (env.BRANCH_NAME == 'master') {
                          pushDockerImage(lambdaapigatewayImage, ['latest', 'prod', rev] as String[])
                          deployWithAsgAndJanus("janus/lambdaapigateway-deployment-prod-spec.yaml", "-p env=prod -p tag=${rev}")
                        }
                         
                      }
                }
            }
        }
    }
}

def gradleBuild(String cmd, String dockerParams = '', String jdkImage = openJDKBuildImage) {
  result = sh(
    returnStdout: true,
    script: 'docker run --rm --volume $(pwd):/lambdaapigateway --volume ~/.gradle:/root/.gradle --volume /var/run/docker.sock:/var/run/docker/sock --workdir /lambdaapigateway ' + dockerParams + " $jdkImage " + cmd
  ).trim()

  echo result
  return result
}
