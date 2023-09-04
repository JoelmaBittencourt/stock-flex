#!/bin/bash

if [ "$EUID" -ne 0 ]; then
    echo "Este script requer privilégios de superusuário. Execute com sudo."
    exit
fi

echo "Instalando o Docker..."
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
sudo usermod -aG docker $USER
sudo systemctl enable docker
sudo systemctl start docker
rm get-docker.sh

echo "Instalando o Docker Compose..."
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

echo "Instalando o Java 17..."
sudo apt update
sudo apt install openjdk-17-jdk -y

echo "Baixando e instalando o Postman..."
sudo snap install postman

echo "Baixando o IntelliJ IDEA..."
sudo snap install intellij-idea-community --classic
