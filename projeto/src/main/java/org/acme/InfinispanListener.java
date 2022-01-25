package org.acme;

import org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryExpired;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryModified;
import org.infinispan.client.hotrod.annotation.ClientListener;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryExpiredEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;

@ClientListener
public class InfinispanListener {

    @ClientCacheEntryCreated
//     {@link org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated},
//  * {@link org.infinispan.client.hotrod.annotation.ClientCacheEntryModified},
//  * {@link org.infinispan.client.hotrod.annotation.ClientCacheEntryRemoved},
//  * {@link org.infinispan.client.hotrod.annotation.ClientCacheFailover}

    public void created(ClientCacheEntryCreatedEvent<String> event) {
         System.out.println("InfinispanListener.created() "+event);
     }

     @ClientCacheEntryModified
     public void modified(ClientCacheEntryModifiedEvent<String> event) {
         System.out.println("InfinispanListener.modified() "+event);
     }

     @ClientCacheEntryExpired
     public void expired(ClientCacheEntryExpiredEvent<String> event) {
        System.out.println("InfinispanListener.expired() "+event);
     }
}
