package com.flipkart.test.FlipKartNewsFeed;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.shell.test.ShellTestClient.NonInteractiveShellSession;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import java.util.concurrent.TimeUnit;

@ShellTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class NewsFeedApplicationTests {

	@Autowired
	ShellTestClient client;

	@Test
	void test() {
		NonInteractiveShellSession session = client.nonInterative("help", "help").run();
		/*await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
			ShellAssertions.assertThat(session.screen()).containsText("AVAILABLE COMMANDS");
		});*/
	}

}
