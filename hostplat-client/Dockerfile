FROM node:10.13.0
MAINTAINER daka
RUN mkdir /usr/src/app
WORKDIR /usr/src/app
RUN npm install -g @angular/cli@7.3.8
COPY hostplat-client/ /usr/src/app
RUN cd /usr/src/app
RUN ls
CMD npm install && ng serve --host 0.0.0.0 --port 4200 --disable-host-check