# JAICF Caila Bot template

Here is a ready to use [JAICF](https://github.com/just-ai/jaicf-kotlin) bot template that can be ran locally or deployed to Heroku server.

# How to use

Please refer to the detailed [Quick Start](https://github.com/just-ai/jaicf-kotlin/wiki/Quick-Start) that shows how to use this template with JAICP and CAILA NLU services.

# Deploy to Heroku

If you would like to deploy this project to the Heroku cloud, just click on the button below

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

1. After deployment open the app and copy the URL of the app to clipboard.
2. Then go to JAICP Web Interface, open your project's settings, select _webhook type_ of connection and paste your URL.
3. That's it! Now all the channels will communicate through your bot deployed to Heroku.

> You can switch project back to long polling instead of webhook once you need to route all messages to your local machine during development.
