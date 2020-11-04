# JAICF Caila Bot template

Here is a ready to use [JAICF](https://github.com/just-ai/jaicf-kotlin) bot template that can be ran locally or deployed to JAICP Cloud or Heroku server.

# How to use

Please refer to the detailed [Quick Start](https://github.com/just-ai/jaicf-kotlin/wiki/Quick-Start) that shows how to use this template with [JAICP](https://github.com/just-ai/jaicf-kotlin/tree/master/channels/jaicp) and [CAILA NLU](https://github.com/just-ai/jaicf-kotlin/tree/master/activators/caila) services.

# Deploy to [JAICP Cloud](https://github.com/just-ai/jaicf-kotlin/wiki/JAICP-Cloud)

JAICP Cloud provides one-click deploy for your bot, just click on the button below

[![Deploy](https://just-ai.com/img/deploy-to-jaicp.svg)](https://app.jaicp.com/deploy)

After deployment just add one or more channels on the _Channels_ page accordingly to your `JaicpServer.kt` configuration.

# Deploy to [Heroku](https://github.com/just-ai/jaicf-kotlin/wiki/Heroku)

If you would like to deploy this project to the Heroku cloud, just click on the button below

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

1. After deployment open the app and copy the URL of the app to clipboard.
2. Then go to JAICP Web Interface, open your project's settings, select _webhook type_ of connection and paste your URL.
3. That's it! Now all the channels will communicate through your bot deployed to Heroku.

> You can switch project back to long polling instead of webhook once you need to route all messages to your local machine during development.
