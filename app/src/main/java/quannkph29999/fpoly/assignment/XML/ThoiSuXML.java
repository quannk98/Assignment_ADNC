package quannkph29999.fpoly.assignment.XML;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import quannkph29999.fpoly.assignment.Model.News;

public class ThoiSuXML {
    String txtContext;
    List<News> thoisulist = new ArrayList<>();
    News objthoisu;

    public List<News> getlistthoisu(InputStream inputStream) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            objthoisu = new News();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        txtContext = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (objthoisu != null) {
                            if (tagName.equalsIgnoreCase("title")) {
                                objthoisu.setTitle(txtContext);
                            } else if (tagName.equalsIgnoreCase("description")) {
                                objthoisu.setDescription(txtContext);
                            } else if (tagName.equalsIgnoreCase("pubDate")) {
                                objthoisu.setPubDate(txtContext);
                            } else if (tagName.equalsIgnoreCase("link")) {
                                objthoisu.setLink(txtContext);
                            } else if (tagName.equalsIgnoreCase("item")) {
                                thoisulist.add(objthoisu);
                            }
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }
            inputStream.close();
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return thoisulist;
    }
}
