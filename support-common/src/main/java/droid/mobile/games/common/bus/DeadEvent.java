package droid.mobile.games.common.bus;

public class DeadEvent {

    public final Object source;
    public final Object event;

    /**
     * Creates a new DeadEvent.
     *
     * @param source object broadcasting the DeadEvent (generally the {@link Bus}).
     * @param event the event that could not be delivered.
     */
    public DeadEvent(Object source, Object event) {
        this.source = source;
        this.event = event;
    }

}