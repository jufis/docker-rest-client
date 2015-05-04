#Quick guide on how to setup private docker registry on SSL

I'm assuming a fedora 20 linux 64bit box here.

First install docker-registry with yum:

>sudo yum install -y docker-registry

The install httpd,mod_ssl and openssl:

>sudo yum install -y httpd

>sudo yum install -y mod_ssl

>sudo yum install -y openssl

Create a ssl dir as root user:

>sudo mkdir /etc/httpd/ssl

Create certificates for the server:

>cd /etc/httpd/ssl

>openssl genrsa -des3 -out server.key 1024

>openssl req -new -key server.key -out server.csr

>cp server.key server.key.org

>openssl rsa -in server.key.org -out server.key

>openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt

Create apache docker.conf:

>touch /etc/httpd/conf.d/docker.conf

Add the following to the docker.conf file:

'''

		<VirtualHost *:443>
	        ServerName registry.jufis.net
	        ServerAlias registry.jufis.net
	
	        SSLEngine on
	        SSLCertificateFile /etc/httpd/ssl/server.crt
	        SSLCertificateKeyFile /etc/httpd/ssl/server.key
	
	        Header set Host "registry.jufis.net"
	        RequestHeader set X-Forwarded-Proto "https"
	        ProxyRequests     off
	        ProxyPreserveHost on
	        ProxyPass         / http://127.0.0.1:5000/
	        ProxyPassReverse  / http://127.0.0.1:5000/
	        ErrorLog /etc/httpd/logs/registry-error.log
	        LogLevel warn
	        CustomLog /etc/httpd/logs/registry-access.log combined
	        <Location />
	                Order deny,allow
	                Allow from all
	        </Location>
	        <Location /v1/_ping>
	                Satisfy any
	                Allow from all
	        </Location>
	        <Location /_ping>
	               Satisfy any
	               Allow from all
	        </Location>
		</VirtualHost>
'''

Start apache:

>systemctl start httpd

Start registry:

>systemctl start docker-registry

Check ports are open:

>netstat -an | grep -e 5000 -e 443

Stop firewall:

>systemctl stop firewalld


