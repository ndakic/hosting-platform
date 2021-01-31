FROM node:10.13.0
LABEL MAINTAINER="daka"
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
RUN npm install -g @angular/cli@7.3.8
COPY ./ /usr/src/app
RUN cd /usr/src/app
CMD npm install && ng serve --host 0.0.0.0