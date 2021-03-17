var context = $evaluation.getContext();
var identity = context.getIdentity();
var attributes = identity.getAttributes();

LOG.info(script.name + " --> trace auth for: " + user.username);

$evaluation.grant();
