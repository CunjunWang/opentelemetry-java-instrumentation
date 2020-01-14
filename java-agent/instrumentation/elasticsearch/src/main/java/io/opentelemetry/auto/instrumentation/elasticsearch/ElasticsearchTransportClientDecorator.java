package io.opentelemetry.auto.instrumentation.elasticsearch;

import io.opentelemetry.auto.api.MoreTags;
import io.opentelemetry.auto.api.SpanTypes;
import io.opentelemetry.auto.decorator.DatabaseClientDecorator;
import io.opentelemetry.auto.instrumentation.api.AgentSpan;

public class ElasticsearchTransportClientDecorator extends DatabaseClientDecorator {
  public static final ElasticsearchTransportClientDecorator DECORATE =
      new ElasticsearchTransportClientDecorator();

  @Override
  protected String[] instrumentationNames() {
    return new String[] {"elasticsearch"};
  }

  @Override
  protected String service() {
    return "elasticsearch";
  }

  @Override
  protected String component() {
    return "elasticsearch-java";
  }

  @Override
  protected String spanType() {
    return SpanTypes.ELASTICSEARCH;
  }

  @Override
  protected String dbType() {
    return "elasticsearch";
  }

  @Override
  protected String dbUser(final Object o) {
    return null;
  }

  @Override
  protected String dbInstance(final Object o) {
    return null;
  }

  public AgentSpan onRequest(final AgentSpan span, final Class action, final Class request) {
    if (action != null) {
      span.setTag(MoreTags.RESOURCE_NAME, action.getSimpleName());
      span.setTag("elasticsearch.action", action.getSimpleName());
    }
    if (request != null) {
      span.setTag("elasticsearch.request", request.getSimpleName());
    }
    return span;
  }
}