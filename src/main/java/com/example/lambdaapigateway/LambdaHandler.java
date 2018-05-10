package com.example.lambdaapigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author jugul.mishra
 * May 10, 2018 3:05:02 PM
 */

public class LambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
	private SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	private final Logger log = LoggerFactory.getLogger(LambdaHandler.class);

	public LambdaHandler(){
		
	}
	// this method register with dispatcher servlet and delegate every request
	public AwsProxyResponse handleRequest(final AwsProxyRequest awsProxyRequest, final Context context) {
		if (handler == null) {
			try {
				final LambdaLogger logger = context.getLogger();
				handler = SpringLambdaContainerHandler.getAwsProxyHandler(LambdaapigatewayApplication.class);

				logger.log(String.format("Remaining time for this lambda -- %s",
						String.valueOf(context.getRemainingTimeInMillis())));
			} catch (ContainerInitializationException e) {
				log.error("Cannot initialize spring handler", e);
				return null;
			}
		}

		return handler.proxy(awsProxyRequest, context);
	}
}
