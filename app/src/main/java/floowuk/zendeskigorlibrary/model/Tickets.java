package floowuk.zendeskigorlibrary.model;

import android.support.annotation.NonNull;

/*** Created by igor on 03/06/2017. */
public final class Tickets {

    private final Integer id;
    private final String subject;
    private final String description;
    private final String status;

    public Tickets (@NonNull Integer id, @NonNull  String subject, @NonNull String description, @NonNull String status)
    {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
