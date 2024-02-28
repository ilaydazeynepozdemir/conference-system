FROM icr.io/instana/agent:latest
ENV INSTANA_DEBUG=true

#java
#RUN curl -LO https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.tar.gz && \
#    tar xvf jdk-17_linux-x64_bin.tar.gz -C /opt && \
#    rm jdk-17_linux-x64_bin.tar.gz
RUN curl -LO https://download.oracle.com/java/17/latest/jdk-17_linux-aarch64_bin.tar.gz && \
    tar xvf jdk-17_linux-aarch64_bin.tar.gz -C /opt && \
    rm jdk-17_linux-aarch64_bin.tar.gz
ENV PATH="/opt/jdk-17.0.10/bin:${PATH}"
RUN ["chmod","+x","/opt/jdk-17.0.10/bin/java"]

EXPOSE 9292
MAINTAINER com.conference.system
COPY /app/target/app-0.0.1-SNAPSHOT.jar /opt/instana/agent/app.jar

#instana karaf
#CMD ["sh /opt/instana/agent/bin/status"]
#app
ENTRYPOINT ["java","-jar","/opt/instana/agent/app.jar"]

WORKDIR /opt/instana/agent
CMD ["sh /bin/run.sh"]



