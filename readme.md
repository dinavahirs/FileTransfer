File transfer application has implementation of 2 functionalities:
1. File Upload (Currently upload directory is pointing to my local, it is required to update appropriate FileUploadController.java to update the same.)
	a. Upload Single file
	b. Upload Multiple files
2. File Download (From the uploaded directory)
	a. Download the file to local downloads folder
	b. To view the downloaded file within the browser (Code is currently commented in FileDownloadController.java). 
	Appropriate notes added to uncomment this line and then comment the other line which is to download the file to local.

Notes:	
1. JSPs are placed under webapp/WEB-INF/jsp folder. JSTL forloops would be ideal in jsps for multiple select files on jsp, but not implemented yet.
2. Error html is under templates folder, ideal this will be moved to jsp folder
3. Test cases are written, but not yet tested.
4. Error handling is implemented by implementing ErrorController interface. 
5. The logic can further be refined, and it was partially attempted, but left it in comments for reference.

URLs:
http://localhost:8080/ (Multiple files are allowed to upload. Atleast one file need to be uploaded, otherwise, on submit, app will be redirected to error page)

http://localhost:8080/download/<<FileName.extension>>
