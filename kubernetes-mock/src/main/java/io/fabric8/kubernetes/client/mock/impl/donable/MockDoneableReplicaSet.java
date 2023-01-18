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

package io.fabric8.kubernetes.client.mock.impl.donable;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.api.model.Doneable;
import io.fabric8.kubernetes.api.model.extensions.DoneableReplicaSet;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSet;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSetBuilder;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSetFluent;
import io.fabric8.kubernetes.api.model.extensions.ReplicaSetFluentImpl;
import io.fabric8.kubernetes.client.mock.MockDoneable;
import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;

public class MockDoneableReplicaSet extends ReplicaSetFluentImpl<MockDoneableReplicaSet> implements MockDoneable<ReplicaSet> {
  private interface DelegateInterface extends Doneable<ReplicaSet>, ReplicaSetFluent<DoneableReplicaSet> {}
  private final Function<ReplicaSet, ReplicaSet> visitor = new Function<ReplicaSet, ReplicaSet>() {
    @Override
    public ReplicaSet apply(ReplicaSet item) {return item;}
  };

  private final DelegateInterface delegate;

  public MockDoneableReplicaSet() {
    super(new ReplicaSetBuilder()
      .withNewMetadata().endMetadata()
      .build());
    this.delegate = EasyMock.createMock(DelegateInterface .class);
  }

  @Override
  public IExpectationSetters<ReplicaSet> done() {
    return EasyMock.expect(delegate.done());
  }

  @Override
  public Void replay() {
    EasyMock.replay(delegate);
    return null;
  }

  @Override
  public void verify() {
    EasyMock.verify(delegate);
  }

  @Override
  public Doneable<ReplicaSet> getDelegate() {
    return new DoneableReplicaSet(new ReplicaSetBuilder(this).build(), visitor) {
      @Override
      public ReplicaSet done() {
        return delegate.done();
      }
    };
  }
}
