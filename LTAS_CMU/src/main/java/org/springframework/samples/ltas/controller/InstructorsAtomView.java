
package org.springframework.samples.ltas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.samples.ltas.entity.Instructor;
import org.springframework.samples.ltas.entity.Instructors;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;

public class InstructorsAtomView extends AbstractAtomFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
        feed.setId("tag:springsource.org");
        feed.setTitle("Instructors");
        //feed.setUpdated(date);
    }

    @Override
    protected List<Entry> buildFeedEntries(Map<String, Object> model,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception {

        Instructors instructors = (Instructors) model.get("instructors");
        List<Instructor> instructorList = instructors.getInstructorList();
        List<Entry> entries = new ArrayList<Entry>(instructorList.size());

        for (Instructor instructor : instructorList) {
            Entry entry = new Entry();
            // see http://diveintomark.org/archives/2004/05/28/howto-atom-id#other
            entry.setId(String.format("tag:springsource.org,%s", instructor.getId()));
            entry.setTitle(String.format("Instructor: %s %s", instructor.getFirstName(), instructor.getLastName()));
            //entry.setUpdated(visit.getDate().toDate());

            Content summary = new Content();
            summary.setValue(instructor.getFaculties().toString());
            entry.setSummary(summary);

            entries.add(entry);
        }
        response.setContentType("blabla");
        return entries;

    }

}
