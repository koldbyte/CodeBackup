CodeBack is a tool to download all your code submissions from code contest sites &mdash; Codeforces, Spoj and Codechef

GITHUB : [https://github.com/koldbyte/CodeBackup](https://github.com/koldbyte/CodeBackup)

Update v2:  
I have updated the app.  
It is now Java7 compatible.   
I also added new features &mdash; Fetch all AC submissions.   
It also includes some bug fixes.  
[Download here v2 ](https://github.com/koldbyte/CodeBackup/releases/download/Codeback_v2/CodeBackup_v2.jar)
  
  				
How to use it?  
  
	1. Enable the checkbox for which you want to fetch submissions.  
	2. Enter your handle(username) registered on the website.  
	3. Select a directory where you want to save the codes  
	4, Select other options as required.  
	5. Hit Run.  
				  
CodeBack will save all the Codes and Problem Statement in following directory format :  
(Select Directory) / (Handle) / (ContestSite) / (ProblemName) / (ProblemName)-(SubmissionId).(Ext)

Features:   
1) Sites supported &mdash; Codeforces, Spoj and Codechef  
2) Fetches Problem statement too  
3) Option to overwrite/not overwrite existing code.  
4) Cross-platform  

Screenshot::  
  
![ ](http://i.imgur.com/CMqeiP3.png)
  
Credits: Used some idea from https://github.com/ideamonk/spojbackup for Spoj  

TODO::  
1) For fetching code of Codeforces gym submissions, password is also required. So currently, no gym submissions will be downloaded.   
2) Add check to verify if the handle is valid.  
