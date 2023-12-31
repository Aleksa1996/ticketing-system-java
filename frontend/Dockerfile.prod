FROM node:20.10.0-bookworm

WORKDIR /var/www/html

COPY package.json package-lock.json /var/www/html/

EXPOSE 80

CMD bash -c "npm install && npm start"