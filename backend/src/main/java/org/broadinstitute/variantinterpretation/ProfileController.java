package org.broadinstitute.variantinterpretation;

import org.broadinstitute.variantinterpretation.api.ProfileApi;
import org.broadinstitute.variantinterpretation.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProfileController implements ProfileApi {

  private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

  private final String ownerEmail;

  public ProfileController(@Value("${WORKBENCH_USER_EMAIL:}") String ownerEmail) {
    this.ownerEmail = ownerEmail;
  }

  @Override
  public ResponseEntity<UserProfile> profile() {
    Map<String, String> env = new HashMap<>();
    for (String key : List.of("OWNER_EMAIL", "GOOGLE_PROJECT", "WORKBENCH_USER_EMAIL")) {
      String v = System.getenv(key);
      if (v != null) env.put(key, v);
    }
    return ResponseEntity.ok(new UserProfile().userEmail(ownerEmail).env(env));
  }
}
