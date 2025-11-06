import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.accelerate.client.queue.QueueBasedImplementationRunner
import io.accelerate.client.runner.ChallengeSession
import runner.UserInputAction
import runner.Utils.getConfig
import runner.Utils.getRunnerConfig

/**
 * ~~~~~~~~~~ Running the system: ~~~~~~~~~~~~~
 *
 *   From IDE:
 *      Run this file from the IDE.
 *
 *   From command line:
 *      ./gradlew run
 *
 *   To run your unit tests locally:
 *      ./gradlew test -i
 *
 * ~~~~~~~~~~ The workflow ~~~~~~~~~~~~~
 *
 *   By running this file you interact with a challenge server.
 *   The interaction follows a request-response pattern:
 *        * You are presented with your current progress and a list of actions.
 *        * You trigger one of the actions by typing it on the console.
 *        * After the action feedback is presented, the execution will stop.
 *
 *   +------+-----------------------------------------------------------------------+
 *   | Step | The usual workflow                                                    |
 *   +------+-----------------------------------------------------------------------+
 *   |  1.  | Run this file.                                                        |
 *   |  2.  | Start a challenge by typing "start".                                  |
 *   |  3.  | Read the description from the "challenges" folder.                    |
 *   |  4.  | Locate the file corresponding to your current challenge in:           |
 *   |      |   ./src/main/kotlin/solutions                                         |
 *   |  5.  | Replace the following placeholder exception with your solution:       |
 *   |      |   TODO("Solution not implemented")                                    |
 *   |  6.  | Deploy to production by typing "deploy".                              |
 *   |  7.  | Observe the output, check for failed requests.                        |
 *   |  8.  | If passed, go to step 1.                                              |
 *   +------+-----------------------------------------------------------------------+
 *
 *   You are encouraged to change this project as you please:
 *        * You can use your preferred libraries.
 *        * You can use your own test framework.
 *        * You can change the file structure.
 *        * Anything really, provided that this file stays runnable.
 *
 *
 *
 *
 * CHK_R5
 * ROUND 5 - A new offer type + price updates
 * All the other major supermarket have adopted a new offer type: group discount offer.
 * The offer could be presented like: buy any 3 of a group of items for 45
 * To keep up with the market, we need to make some price adjustments.
 * Please use the new and updated price table.
 *
 * Our price table and offers:
 * +------+-------+---------------------------------+
 * | Item | Price | Special offers                  |
 * +------+-------+---------------------------------+
 * | A    | 50    | 3A for 130, 5A for 200          |
 * | B    | 30    | 2B for 45                       |
 * | C    | 20    |                                 |
 * | D    | 15    |                                 |
 * | E    | 40    | 2E get one B free               |
 * | F    | 10    | 2F get one F free               |
 * | G    | 20    |                                 |
 * | H    | 10    | 5H for 45, 10H for 80           |
 * | I    | 35    |                                 |
 * | J    | 60    |                                 |
 * | K    | 70    | 2K for 120                      |
 * | L    | 90    |                                 |
 * | M    | 15    |                                 |
 * | N    | 40    | 3N get one M free               |
 * | O    | 10    |                                 |
 * | P    | 50    | 5P for 200                      |
 * | Q    | 30    | 3Q for 80                       |
 * | R    | 50    | 3R get one Q free               |
 * | S    | 20    | buy any 3 of (S,T,X,Y,Z) for 45 |
 * | T    | 20    | buy any 3 of (S,T,X,Y,Z) for 45 |
 * | U    | 40    | 3U get one U free               |
 * | V    | 50    | 2V for 90, 3V for 130           |
 * | W    | 20    |                                 |
 * | X    | 17    | buy any 3 of (S,T,X,Y,Z) for 45 |
 * | Y    | 20    | buy any 3 of (S,T,X,Y,Z) for 45 |
 * | Z    | 21    | buy any 3 of (S,T,X,Y,Z) for 45 |
 * +------+-------+---------------------------------+
 *
 *
 * Notes:
 *  - The policy of the supermarket is to always favor the customer when applying special offers.
 *  - Offers involving multiple items always give a better discount than offers containing fewer items.
 *  - For any illegal input return -1
 *
 * In order to complete the round you need to implement the following method:
 *
 * checkout(string) -> integer
 *  - param[0] = a string containing the SKUs of all the products in the basket
 *  - @return = an integer representing the total checkout value of the items
 **/
fun main(args: Array<String>) {
    val entry = EntryPointMapping()

    val runner = QueueBasedImplementationRunner.Builder()
        .setConfig(getRunnerConfig())
        .withSolutionFor("sum", entry::sum)
        .withSolutionFor("hello", entry::hello)
        .withSolutionFor("fizz_buzz", entry::fizzBuzz)
        .withSolutionFor("checkout", entry::checkout)
        .withSolutionFor("rabbit_hole", entry::rabbitHole)
        .withSolutionFor("amazing_maze", entry::amazingMaze)
        .withSolutionFor("ultimate_maze", entry::ultimateMaze)
        .withSolutionFor("increment", entry::increment)
        .withSolutionFor("to_uppercase", entry::toUppercase)
        .withSolutionFor("letter_to_santa", entry::letterToSanta)
        .withSolutionFor("count_lines", entry::countLines)
        .withSolutionFor("array_sum", entry::arraySum)
        .withSolutionFor("int_range", entry::intRange)
        .withSolutionFor("filter_pass", entry::filterPass)
        .withSolutionFor("inventory_add", entry::inventoryAdd)
        .withSolutionFor("inventory_size", entry::inventorySize)
        .withSolutionFor("inventory_get", entry::inventoryGet)
        .withSolutionFor("waves", entry::waves)
        .withJacksonModule(KotlinModule.Builder().build())
        .create()

    ChallengeSession.forRunner(runner)
        .withConfig(getConfig())
        .withActionProvider(UserInputAction(args))
        .start()
}

