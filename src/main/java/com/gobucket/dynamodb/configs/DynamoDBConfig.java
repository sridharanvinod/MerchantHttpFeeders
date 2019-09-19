package com.gobucket.dynamodb.configs;

import javax.annotation.PostConstruct;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.gobucket.dynamodb.repositories.models.coupon.Coupon;



@Configuration
@EnableDynamoDBRepositories
(basePackages = "com.gobucket.dynamodb.repositories")
public class DynamoDBConfig {
	
	@Value("${amazon.dynamodb.endpoint}")
    private String dynamoDbEndpoint;

    @Value("${amazon.aws.accessKey}")
    private String awsAccessKey;

    @Value("${amazon.aws.secretKey}")
    private String awsSecretKey;
	
	@PostConstruct
	@Bean(name = "dynamoDBProxyServer")
	public DynamoDBProxyServer dynamoDBProxyServer() throws Exception {
		//System.setProperty("java.library.path", "/Users/A336030/.m2/repository/com/almworks/sqlite4java/sqlite4java/1.0.392");
		final String[] localArgs = { "-inMemory" };
		//final String[] localArgs = { "-inMemory", "-port", "13005" };
		DynamoDBProxyServer server = ServerRunner.createServerFromCommandLineArgs(localArgs);
		server.start();
		return server;
	}

	@PostConstruct
	@DependsOn("dynamoDBProxyServer")
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		// Create an in-memory and in-process instance of DynamoDB Local that runs over
		// HTTP
		AmazonDynamoDB dynamodb = AmazonDynamoDBClientBuilder.standard()
									.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDbEndpoint, "us-west-2"))
									.withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
									.build();
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamodb);
		CreateTableRequest tableRequest = dynamoDBMapper
		        .generateCreateTableRequest(Coupon.class);

		tableRequest.setProvisionedThroughput(
		        new ProvisionedThroughput(1l,1l));

		/*tableRequest.getGlobalSecondaryIndexes()
			.get(0).setProvisionedThroughput(new ProvisionedThroughput(1l, 1l));
*/
		
		TableUtils.createTableIfNotExists(dynamodb, tableRequest);
	
		// use the DynamoDB API over HTTP
		 listTables(dynamodb.listTables(), "DynamoDB Local over HTTP");
		return dynamodb;
	}
	@Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }
	private void listTables(ListTablesResult result, String method) {
		System.out.println("found " + Integer.toString(result.getTableNames().size()) + " tables with " + method);
		for (String table : result.getTableNames()) {
			System.out.println(table);
		}
	}
}
