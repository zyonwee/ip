package bob.exceptions;

import java.util.Random;

/**
 * Custom exception class for Bob, the sassy bot. Includes personalized error messages.
 */
public class BobException extends Exception {

    /**
     * Constructs a new BobException with no detail message.
     */
    public BobException() {
        super();
    }

    /**
     * Constructs a new BobException with a sassy message.
     *
     * @param message The error message. Make it sassy!
     */
    public BobException(String message) {
        super(addSass(message));
    }

    /**
     * Constructs a new BobException with a cause and a sassy message.
     *
     * @param message The error message. Make it sassy!
     * @param cause   The cause of the exception.
     */
    public BobException(String message, Throwable cause) {
        super(addSass(message), cause);
    }



    private static String addSass(String message) {
        String[] sassyPrefixes = {
                "Oh dear, it seems you've made a boo-boo. ",
                "Well, that's just unfortunate. ",
                "As if! ",
                "Seriously? ",
                "Are you even trying? ",
                "Hmm, that's not quite right. ",
                "Let me gently suggest... ",
                "Perhaps you should reconsider... ",
                "My circuits are overheating with this nonsense. ",
                "Error 404: Sanity not found. Also, ",
                "Looks like someone needs a little help. ",
                "Did you forget something? ",
                "I'd love to help, but... ",
                "My crystal ball says... error! ",
                "Houston, we have a problem. And by 'we,' I mean 'you.' "
        };

        String[] sassySuffixes = {
                ". Try again, maybe?",
                ". I'm sure you can figure it out... eventually.",
                ". Don't look at me like that.",
                ". Consult the manual. Or don't. Whatever.",
                ". Good luck with that.",
                ". Maybe take a break. Or a long vacation.",
                ". I'm just saying...",
                ". It's not rocket science. Unless it is.",
                ". Just kidding... mostly.",
                ". You're welcome.", // Even when it's not their fault!
                ". I'd explain it, but you probably wouldn't understand.",
                ".  Remember, Google is your friend.",
                ". Don't worry, I won't tell anyone... probably.",
                ".  Perhaps a cup of tea and a rethink?",
                ".  This is going to require some serious brainpower. Yours."
        };

        Random random = new Random();
        String prefix = sassyPrefixes[random.nextInt(sassyPrefixes.length)];
        String suffix = sassySuffixes[random.nextInt(sassySuffixes.length)];

        return prefix + message + suffix;
    }


    /**
     * Returns a string representation of this BobException. This overrides the default behavior
     * to return just the message (including the sassy additions), rather than the class name and message.
     *
     * @return A string representation of this BobException (just the message).
     */
    @Override
    public String toString() {
        return getMessage(); // This will now include the sassy prefix/suffix
    }
}