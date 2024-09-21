FROM alpine:3.20 AS yimin_code_train
LABEL maintainer="hymlaucs@gmail.com"

# Install Java, C++, Go, and Python
RUN apk update \
  && apk upgrade \
  && apk add --no-cache ca-certificates \
  && update-ca-certificates \
  && apk add --no-cache coreutils \
  && apk add --no-cache openjdk21 tzdata curl unzip bash \
  && apk add --no-cache nss \
  && apk add --no-cache g++ gcc make \
  && apk add --no-cache go \
  && apk add --no-cache python3=3.12.0-r0 py3-pip \
  && rm -rf /var/cache/apk/*

# Verify installations
RUN java -version \
  && g++ --version \
  && go version \
  && python3 --version
