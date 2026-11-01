# Use lightweight Nginx Alpine image as the base for serving static frontend pages
FROM nginx:alpine

# Copy all static frontend files to Nginx's default web root directory
COPY . /usr/share/nginx/html

# Expose port 80 for HTTP access to the calculator web page
EXPOSE 80
