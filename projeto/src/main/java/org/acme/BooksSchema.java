package org.acme;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = { Livro.class }, schemaPackageName = "nome_pacote")
interface MySchemaBuilder extends GeneratedSchema {
}
