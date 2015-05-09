package sscoble.helpers

/**
 * Created by sscoble on 5/7/15.
 */
trait GetRandom {
    def methodMissing(String name, args) {
        if (name.startsWith("getRandom")) {
            UUID.randomUUID().toString()
        } else throw new MissingMethodException(name, this.class, args)
    }
}