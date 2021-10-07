# JAICF Caila Bot template

Here is a ready to use [JAICF](https://github.com/just-ai/jaicf-kotlin) bot template that can be ran locally or deployed to JAICP Cloud or Heroku server.

# How to use

Please refer to the detailed [Quick Start](https://help.jaicf.com/Quick-Start) that shows how to use this template with [JAICP](https://help.jaicf.com/JAICP) and [CAILA NLU](https://help.jaicf.com/Caila) services.

# Deploy to [Heroku](https://help.jaicf.com/Heroku)

If you would like to deploy this project to the Heroku cloud, just click on the button below

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

1. After deployment open the app and copy the URL of the app to clipboard.
2. Then go to JAICP Web Interface, open your project's settings, select _webhook type_ of connection and paste your URL.
3. That's it! Now all the channels will communicate through your bot deployed to Heroku.

> You can switch project back to long polling instead of webhook once you need to route all messages to your local machine during development.

# Docker

To build and run Docker image:

1. Build project with `stage` gradle task
2. Build Docker image with `docker build -t jaicf-jaicp-caila-template .`
3. Run Docker image with `docker run -p 8080:8080 -e JAICP_API_TOKEN=<your JAICP project token> jaicf-jaicp-caila-template`

Note that this builds a webhook version of your JAICF bot.
Thus, you have to propagate your local 8080 port to the global web (through some gateway like ngrok) and configure your JAICP project with direct public URL of your machine.

## Java options

To pass java options to your Docker image just run it with [JAVA_TOOL_OPTIONS](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/envvars002.html) environment like:

`docker run -p 8080:8080 -e JAICP_API_TOKEN=<your JAICP project token> -e "JAVA_TOOL_OPTIONS=-Xms1024m -Xmx2048m" jaicf-jaicp-caila-template`
