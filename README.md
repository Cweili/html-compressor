RSS Creator
===========

RSS 2.0
-----------
See [RSS 2.0 at Harvard Law](http://cyber.law.harvard.edu/rss/rss.html) for more details.

Atom 1.0
-----------
See [RFC 4278](http://tools.ietf.org/html/rfc4287) for more details.

Dependency
-----------
[Apache Commons Lang 3](http://commons.apache.org/proper/commons-lang/)

Examples
-----------

Generate default Chinese RSS 2.0 feed
```java
	/**
	 * Generate default Chinese RSS feed.
	 * 
	 * @param title
	 *            the title
	 * @param link
	 *            the link
	 * @param atomLink
	 *            the atom link
	 * @param description
	 *            the description
	 * @param generator
	 *            the specified generator
	 * @param lastBuildDate
	 *            the specified last build date
	 */
	final RSS rss = new RSS("The title", "The link to your homepage", "The atom link",
			"The description", "The generator", new Date());
	
	/**
	 * Adds the specified item.
	 * 
	 * @param title
	 *            the title
	 * @param link
	 *            the permalink
	 * @param guid
	 *            the specified GUID
	 * @param author
	 *            the author
	 * @param description
	 *            the description
	 * @param pubDate
	 *            the specified publish date
	 * @param categories
	 *            the categories
	 */
	rss.addItem("Item1 Title", "The link to your item", "Item GUID 1", "The author",
			"The description", new Date(), new String[] { "Category 1", "Category 2" });
	
	List<String> categories = new ArrayList<String>();
	categories.add("Category 3");
	categories.add("Category 4");
	/**
	 * Adds the specified item.
	 * 
	 * @param title
	 *            the title
	 * @param link
	 *            the permalink
	 * @param author
	 *            the author
	 * @param description
	 *            the description
	 * @param pubDate
	 *            the specified publish date
	 * @param categories
	 *            the categories
	 */
	rss.addItem("Item2 Title", "The link to your item", "The author", "The description",
			new Date(), categories);
	
	System.out.println(rss);
```
- - - - - -

Generate Atom 1.0 feed.
```java
	/**
	 * Generate Atom feed.
	 * 
	 * @param title
	 *            the title
	 * @param subtitle
	 *            the subtitle
	 * @param link
	 *            the link
	 * @param author
	 *            the author
	 * @param id
	 *            the specified id
	 * @param generator
	 *            the generator
	 * @param updated
	 *            the specified updated time
	 */
	final Atom atom = new Atom("The title", "The subtitle", "The link to your homepage",
			"The author", "The atom link", "The generator", new Date());
	
	/**
	 * Adds the specified entry.
	 * 
	 * @param title
	 *            the title
	 * @param link
	 *            the link
	 * @param id
	 *            the specified id
	 * @param author
	 *            the author
	 * @param uri
	 *            the author uri
	 * @param summary
	 *            the summary
	 * @param updated
	 *            the specified updated time
	 * @param categories
	 *            the categories
	 */
	atom.addEntry("Entry1 title", "The link to your entry", "The entry id", "The author",
			"The link to author", "The summary", new Date(), new String[] { "Category 1",
					"Category 2" });
	
	List<String> categories = new ArrayList<String>();
	categories.add("Category 3");
	categories.add("Category 4");
	/**
	 * Adds the specified entry.
	 * 
	 * @param title
	 *            the title
	 * @param link
	 *            the link
	 * @param author
	 *            the author
	 * @param summary
	 *            the summary
	 * @param updated
	 *            the specified updated time
	 * @param categories
	 *            the categories
	 */
	atom.addEntry("Entry1 title", "The link to your entry", "The author", "The summary",
			new Date(), categories);
	
	System.out.println(atom);
```