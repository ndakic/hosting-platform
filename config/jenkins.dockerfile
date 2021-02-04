FROM openjdk:11-jdk

## Dependencies required for Jenkins to work
RUN apt-get update && \
	apt-get upgrade -y && \
	apt-get install -y \
		openssh-client \
		curl \
        build-essential \
        gnupg2 \
        redis-tools


## Install Maven
RUN apt-get update -y && apt-get install maven -y

## Jenkis user setup
ENV USER=jenkins USER_ID=1000 USER_GID=1000

USER root
RUN groupadd --gid "${USER_GID}" "${USER}" && \
    useradd \
      --uid ${USER_ID} \
      --gid ${USER_GID} \
      -G root \
      --create-home \
      --shell /bin/bash \
      ${USER}
