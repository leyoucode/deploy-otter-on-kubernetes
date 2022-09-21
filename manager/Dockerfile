FROM toplist-registry.cn-shanghai.cr.aliyuncs.com/88/tl-java:openjdk-8-jre-slim-bullseye

RUN apt update ; \
	apt install -y file netcat-openbsd ; \
    apt clean && rm -rf /var/lib/{apt,dpkg,cache,log}/

ADD manager.deployer-4.2.18.tar.gz /usr/local/

ENV OTTER_HOME /usr/local/manager.deployer-4.2.18
ENV PATH $OTTER_HOME/bin:$PATH

WORKDIR $OTTER_HOME

CMD ["/usr/local/manager.deployer-4.2.18/bin/startup.sh"]
