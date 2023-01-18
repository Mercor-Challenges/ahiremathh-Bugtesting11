/**
 * Copyright (C) 2015 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fabric8.openshift.client.handlers;

import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.api.model.ListOptions;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import java.util.function.Predicate;

import io.fabric8.kubernetes.client.dsl.base.OperationContext;
import okhttp3.OkHttpClient;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ResourceHandler;
import io.fabric8.openshift.api.model.ProjectRequest;
import io.fabric8.openshift.api.model.ProjectRequestBuilder;
import io.fabric8.openshift.client.dsl.internal.ProjectRequestsOperationImpl;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import java.util.concurrent.TimeUnit;

@Component
@Service
public class ProjectRequestHandler implements ResourceHandler<ProjectRequest, ProjectRequestBuilder> {
  @Override
  public String getKind() {
    return ProjectRequest.class.getSimpleName();
  }

  @Override
  public String getApiVersion() {
    return "project.openshift.io/v1";
  }

  @Override
  public ProjectRequest create(OkHttpClient client, Config config, String namespace, ProjectRequest item, boolean dryRun) {
    return new ProjectRequestsOperationImpl(new OperationContext().withOkhttpClient(client).withConfig(config).withDryRun(dryRun)).create();
  }

  @Override
  public ProjectRequest replace(OkHttpClient client, Config config, String namespace, ProjectRequest item, boolean dryRun) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ProjectRequest reload(OkHttpClient client, Config config, String namespace, ProjectRequest item) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ProjectRequestBuilder edit(ProjectRequest item) {
    return new ProjectRequestBuilder(item);
  }

  @Override
  public Boolean delete(OkHttpClient client, Config config, String namespace, DeletionPropagation propagationPolicy, long gracePeriodSeconds, ProjectRequest item, boolean dryRun) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Watch watch(OkHttpClient client, Config config, String namespace, ProjectRequest item, Watcher<ProjectRequest> watcher) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Watch watch(OkHttpClient client, Config config, String namespace, ProjectRequest item, String resourceVersion, Watcher<ProjectRequest> watcher) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Watch watch(OkHttpClient client, Config config, String namespace, ProjectRequest item, ListOptions listOptions, Watcher<ProjectRequest> watcher) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ProjectRequest waitUntilReady(OkHttpClient client, Config config, String namespace, ProjectRequest item, long amount, TimeUnit timeUnit) throws InterruptedException {
    throw new UnsupportedOperationException();
  }

  @Override
  public ProjectRequest waitUntilCondition(OkHttpClient client, Config config, String namespace, ProjectRequest item, Predicate<ProjectRequest> condition, long amount, TimeUnit timeUnit) throws InterruptedException {
    throw new UnsupportedOperationException();
  }
}
