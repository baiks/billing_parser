//package com.equitybank.billing.parser.configuration;
//
//import io.grpc.ManagedChannelBuilder;
//import io.opentelemetry.OpenTelemetry;
//import io.opentelemetry.exporters.jaeger.JaegerGrpcSpanExporter;
//import io.opentelemetry.exporters.logging.LoggingExporter;
//import io.opentelemetry.sdk.OpenTelemetrySdk;
//import io.opentelemetry.sdk.trace.SpanProcessor;
//import io.opentelemetry.sdk.trace.export.SimpleSpansProcessor;
//import io.opentelemetry.trace.Tracer;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//public class TracerConfig {
//
//    @Bean
//    public Tracer tracer() {
//
//        final Tracer tracer = OpenTelemetry.getTracerFactory ().get("com.forrest.levelone");
//        SpanProcessor jaegerProcessor =
//                SimpleSpansProcessor.newBuilder(JaegerGrpcSpanExporter.newBuilder()
//                        .setServiceName("test_parser")
//                        .setChannel(ManagedChannelBuilder.forAddress(
//                                "localhost", 14250).usePlaintext().build())
//                        .build()).build();
//
//        SpanProcessor logProcessor = SimpleSpansProcessor.newBuilder(new LoggingExporter()).build();
//
//        for (SpanProcessor spanProcessor : Arrays.asList(logProcessor, jaegerProcessor)) {
//            OpenTelemetrySdk.getTracerFactory().addSpanProcessor(spanProcessor);
//        }
//        return tracer;
//    }
//}