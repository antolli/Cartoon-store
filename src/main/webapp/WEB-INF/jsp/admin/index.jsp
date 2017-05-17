<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="top.jsp"%>
<spring:url value="/assets/img/" var="img" />
	<div class="container">
	
		<h1>Welcome to the Admin Page</h1>
		<h2>Did you know?</h2>
		<p>
			The invention of the printing press, allowing movable type, established a separation between images and words, the two requiring different methods in order to be reproduced. 
			Early printed material concentrated on religious subjects, but through the 17th and 18th centuries, they began to tackle aspects of political and social life, and also started to satirize and caricature. 
			It was also during this period that the speech bubble was developed as a means of attributing dialogue.
		
			One of the first creators of comics was William Hogarth (1697-1764). Hogarth created seven sets of sequential images on Modern Moral Subjects. 
			One of his works, A Rake's Progress, was composed of a number of canvases, each reproduced as a print, and the eight prints together created a narrative.
		    As printing techniques developed, due to the technological advances of the industrial revolution, magazines and newspapers were established. 
		    These publications utilized illustrations as a means of commenting on political and social issues, such illustrations becoming known as cartoons in the 1840s. 
		    Soon, artists were experimenting with establishing a sequence of images to create a narrative.
		    While surviving works of these periods such as Francis Barlow's A True Narrative of the Horrid Hellish Popish Plot (c.1682) as well as The Punishments of Lemuel Gulliver and A Rake's Progress by William Hogarth (1726), 
		    can be seen to establish a narrative over a number of images, it wasn't until the 19th century that the elements of such works began to crystallise into the comic strip.
		    The speech balloon also evolved during this period, from the medieval origins of the phylacter, a label, usually in the form of a scroll, which identified a character either through naming them or using a short text to explain their purpose.
		    Artists such as George Cruikshank helped codify such phylacters as balloons rather than scrolls, though at this time they were still called labels.
		    They now represented narrative, but for identification purposes rather than dialogue within the work, and artists soon discarded them in favour of running dialogue underneath the panels.
		     Speech balloons were not reintroduced to the form until Richard F. Outcault used them for dialogue.
		</p>
		<img alt="comics story" src="${img}img_story.png">
  		<br>
  		<span style="font-size: xx-small">
  			The first two of six plates in Hogarth's "Marriage à la Mode" series. The first plate depicts the signing of a marriage contract between the wealthy Lord Squanderfield and the bride's poor merchant father.
  			The second plate depicts a morning in the couple's home after a night out. 
			The dog pulls a bonnet out of the husband's pocket which may allude to infidelity as the wife is already wearing a bonnet.
  		</span>
	</div>
	<footer>
		<center>Created by iTrio &copy;</center>
	</footer>
<%@include file="footer.jsp"%>