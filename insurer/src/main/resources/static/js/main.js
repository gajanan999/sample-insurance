$(document).ready(function(){
	
	var date = new Date();
	date.setDate( date.getDate() - 1);
	date.setFullYear( date.getFullYear() - 1 );
	String dateInFormat=new SimpleDateFormat("yyyy-MM-dd").format(date);
  	$("#dob").attr("max","2010-03-10");
 
});