#!/bin/bash
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.1.0.7.0 -Dpackaging=jar -Dfile=ojdbc6.jar -DgeneratePom=true
mvn install:install-file -DgroupId=org.jtester -DartifactId=jtester -Dversion=1.1.8 -Dpackaging=jar -DpomFile=jtester-1.1.8.pom -Dfile=jtester-1.1.8.jar -Dsources=jtester-1.1.8-sources.jar
mvn install:install-file -DgroupId=mockit -DartifactId=jmockit -Dversion=0.999.10 -Dpackaging=jar -DpomFile=jmockit-0.999.10.pom -Dfile=jmockit-0.999.10.jar -Dsources=jmockit-0.999.10-sources.jar
mvn install:install-file -DgroupId=com.alibaba.fastsql -DartifactId=fastsql -Dversion=2.0.0_preview_135 -Dpackaging=jar -DpomFile=fastsql-2.0.0_preview_135.pom -Dfile=fastsql-2.0.0_preview_135.jar -Dsources=fastsql-2.0.0_preview_135-sources.jar
