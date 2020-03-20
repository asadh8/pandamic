package com.entrue.pandamic.config.spring.elasticsearch;

import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.entrue.pandamic.aws.AWSRequestSigningApacheInterceptor;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.entrue.pandamic")
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${aws.elasticsearch.servicename}")
    private String awsServiceName;

    @Value("${aws.elasticsearch.region}")
    private String region;

    @Value("${aws.elasticsearch.endpoint}")
    private String esEndPoint;

    /**
     * Full access
     */
    private String SECURITY_ACCESS_KEY_FA = "MzqRd7JvvsyGyVR9UlJRIEVMahUPJ85zl5JEIFIa";

    private String ACCESS_KEY_ID_FA = "AKIAUXG2R3HSJKTO25GC";

    @Bean( name = "elasticsearchClient", destroyMethod = "close")
    @Override
    public RestHighLevelClient elasticsearchClient() {
        // this access key has read write and list permission
//        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
//                new BasicAWSCredentials("AKIAUXG2R3HSF2Q656V4",
//                        "2tMcJLDf2Rd5ihJ+RxS+usEXfFv/5HGEO/FUpvZs"));
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(ACCESS_KEY_ID_FA,
                        SECURITY_ACCESS_KEY_FA));
        AWS4Signer signer = new AWS4Signer();
        signer.setServiceName(awsServiceName);
        signer.setRegionName(region);
        HttpRequestInterceptor interceptor = new AWSRequestSigningApacheInterceptor(awsServiceName, signer, awsCredentialsProvider);
        return new RestHighLevelClient(RestClient.builder(HttpHost.create(esEndPoint)).setHttpClientConfigCallback(hacb -> hacb.addInterceptorLast(interceptor)));
    }

    @Bean
    @Override
    public EntityMapper entityMapper() {
        ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(elasticsearchMappingContext(),
                new DefaultConversionService());
        entityMapper.setConversions(elasticsearchCustomConversions());
        return entityMapper;
    }
}
