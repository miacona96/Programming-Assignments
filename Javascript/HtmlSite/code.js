var index = 0;
function doSlideShow()
{
	var i;
	var slides = document.getElementsByClassName("slide");
	for(i = 0;i<slides.length;i++){
		slides[i].style.display = "none";
	}
	index++;
	if(index > slides.length){
		index = 1;
	}
	slides[index-1].style.display = "block";
	setTimeout(doSlideShow,4000);
}