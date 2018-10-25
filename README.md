# Sonos API client for Java

This library provides a simple integration with the Sonos API, allowing you to control players and content from any Java app.

Please note that this library is currently still in development. Some aspects (particularly with the SMAPI) cannot be tested
due to a lack of access, others due to not owning devices with specific features.

[![Build Status](https://travis-ci.org/nightowlengineer/sonos-api-java.svg?branch=master)](https://travis-ci.org/nightowlengineer/sonos-api-java)

## Prerequisites

Before using the library, it's recommended that you read the [official documentation](https://developer.sonos.com/build/connected-home-get-started/)
published on the Sonos Developer site. The [reference documentation](https://developer.sonos.com/reference/) is also
detailed and provides a good overview of the many different resources and endpoints open to you.

You'll need to [register with Sonos](https://integration.sonos.com/users/sign_up), [create a Control Integration](https://integration.sonos.com/control_integrations/new),
and finally [generate client credentials](https://integration.sonos.com/integrations) to be able to connect with the Sonos API.
It's also recommended that you have at least one Sonos device to be able to properly test and play with your application
(plus, listening to music or watching movies with Sonos is awesome anyway!)

## Maven Setup

```xml
<dependency>
  <groupId>engineer.nightowl</groupId>
  <artifactId>sonos-api-java</artifactId>
  <version>0.0.2</version>
</dependency>
```

## Getting Started

Create a new configuration object, set your integration's properties (see further above on how to obtain these),
then create a client and pass in that configuration:
```java
final SonosApiConfiguration configuration = new SonosApiConfiguration();
configuration.setApiKey(properties.getProperty("apiKey"));
configuration.setApiSecret(properties.getProperty("apiSecret"));
configuration.setApplicationId(properties.getProperty("applicationId"));

final SonosApiClient client = new SonosApiClient(configuration);
```

Then use the client to view and modify the user's devices - the following types of resource are available:

  * authorize
  * favorite
  * group
  * groupVolume
  * homeTheater
  * household
  * musicServiceAccounts
  * playbackMetadata
  * playback
  * playbackSession
  * playerVolume

For example, to set all 'groups' (aka rooms) to use crossfade when music is playing, you could run the following:

```java
final SonosGroups groups = client.group().getGroups("authToken", "householdId");
final SonosPlayMode playMode = new SonosPlayMode();
playMode.setCrossfade(true);

for (final SonosGroup group : groups.getGroups())
{
	client.playback().setPlayModes("authToken", group.getId(), playMode);
}
```

## Todo

  * Continue to add detailed Javadoc throughout the resources
  * Add unit testing throughout
  * Complete manual testing of all available endpoints
