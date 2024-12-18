# Dow Jones Stock Record Manager

A Spring Boot application to manage and query weekly stock records from the Dow Jones Index dataset.

## Features
- Upload bulk/single datasets.
- Query records by stock ticker.
- Query All records by stock ticker.
- Add a new stock record.

## Technologies
- Spring Boot
- h2-console
- Swagger


## Running the Application
1. Set up h2-console and update `application.properties`.
2. Run the application with 
- mvn clean package
- docker-compose up -d
3. Access Swagger at `http://localhost:8080/swagger-ui/index.html`.
4. Java version 17

## Testing
Run tests with `mvn test`.

## Endpoints
- **POST /api/dow-jones/upload-multiple**: Upload a Multiple datasets.
- **GET /api/dow-jones/query/{stock}**: Query records by stock ticker.
- **GET /api/dow-jones/query**: Query All records by stock ticker.

- # Docs for the Azure Web Apps Deploy action: https://github.com/Azure/webapps-deploy
# More GitHub Actions for Azure: https://github.com/Azure/actions

name: Build and deploy JAR app to Azure Web App - Spring-azure1

on:
  push:
    branches:
      - main
  workflow_dispatch:

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      - name: Set up Java version
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'microsoft'

      - name: Build with Maven
        run: mvn clean install

      - name: Upload artifact for deployment job
        uses: actions/upload-artifact@v4
        with:
          name: java-app
          path: '${{ github.workspace }}/target/*.jar'

  deploy:
    runs-on: ubuntu-latest
    needs: build
    environment:
      name: 'Production'
      url: ${{ steps.deploy-to-webapp.outputs.webapp-url }}
      
    steps:
      - name: Download artifact from build job
        uses: actions/download-artifact@v4
        with:
          name: java-app
      
      - name: Deploy to Azure Web App
        id: deploy-to-webapp
        uses: azure/webapps-deploy@v3
        with:
          app-name: 'Spring-azure1'
          slot-name: 'Production'
          package: '*.jar'
          publish-profile: ${{ secrets.AzureAppService_PublishProfile_d0333523f8734ee4b652c4ac5e73e851 }}
- **POST /api/dow-jones/add**: Add a new record.
