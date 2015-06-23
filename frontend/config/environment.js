/* jshint node: true */

module.exports = function(environment) {
  var ENV = {

    modulePrefix: 'googlanime',
    environment: environment,
    baseURL: '/',
    locationType: 'auto',
    EmberENV: {
      FEATURES: {
        // Here you can enable experimental features on an ember canary build
        // e.g. 'with-controller': true
      }
    },

    APP: {
      // Here you can pass flags/options to your application instance
      // when it is created
    },

    contentSecurityPolicy: {
      'default-src': "'self' http://localhost:8080",
      'script-src': "'self' http://localhost:8080",
      'font-src': "'self' data: use.typekit.net http://localhost:8080",
      'connect-src': "'self' http://localhost:8080",
      'img-src': "'self' http://localhost:8080 www.facebook.com p.typekit.net http://cdn.myanimelist.net",
      'style-src': "'self' 'unsafe-inline' http://localhost:8080 use.typekit.net"
    },

    torii: {
      sessionServiceName: 'session',
      providers: {
        'google-token': {
          apiKey: '476972891723-vkleotsupjaivr8j2ivf19us537acl2s.apps.googleusercontent.com',
          scope: 'profile email',
          redirectUri: 'http://localhost:4200'
        }
      }
    },

    backend: {
      host: 'http://localhost:8080',
      namespace: ''
    }
  }

  if (environment === 'development') {
    // ENV.APP.LOG_RESOLVER = true;
    ENV.APP.LOG_ACTIVE_GENERATION = true;
    // ENV.APP.LOG_TRANSITIONS = true;
    // ENV.APP.LOG_TRANSITIONS_INTERNAL = true;
    ENV.APP.LOG_VIEW_LOOKUPS = true;
  }

  if (environment === 'test') {
    // Testem prefers this...
    ENV.baseURL = '/';
    ENV.locationType = 'auto';

    // keep test console output quieter
    ENV.APP.LOG_ACTIVE_GENERATION = false;
    ENV.APP.LOG_VIEW_LOOKUPS = false;

    ENV.APP.rootElement = '#ember-testing';
  }

  if (environment === 'production') {

  }

  ENV['simple-auth'] = {
    routeAfterAuthentication: 'search'
  };

  return ENV;
};
