VERSAO = $(shell cat .VERSAO)
PROGNAME = myzkx
DISTNAME = $(PROGNAME)-$(VERSAO)
java_home = /usr/lib/jvm/jdk1.7.0_55
mvn = /home/higor/Programas/netbeans-8.0.2/java/maven/bin/mvn

all: build

build:
	JAVA_HOME=$(java_home) $(mvn) clean install

install:
	cp target/*.jar ../../lib/java

clean:
	JAVA_HOME=$(java_home) $(mvn) clean
