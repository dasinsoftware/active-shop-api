FROM adoptopenjdk:11-jre-openj9
VOLUME /tmp
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","-Xshareclasses","-XX:SharedCacheHardLimit=200m","-Xscmx60m","-Xtune:virtualized","za.co.momentum.activeshopapi.ActiveShopApiApplication"]
