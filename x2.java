//////// x2 WIP
////////Nick Ferro  (CST 112; Sept 20 2015)
/////// WIP, Incomplete, more features planned/in-progress

//// GLOBALS:  coordinates, speed, etc.
float x, y;       // Position of emblem.
float ex, ey;     // emblem Speed.
float cx, cy;   // chaser speed.
float sx,sy;      //sun position
float mx,my;      //moon position
int state;        //toggle state for sun and moon
float horizon;
float gravity;
float [] xpos  = new float[41];
float [] a50   = new float[41];
float [] a210  = new float[41];
float [] shade = new float[41];
float [] len   = new float[41];


//// SETUP:  window size, initialization (start in middle of screen).
void setup() {
  size( 640,480);
  horizon=  height/4;
  x=  width/2;
  y=  height/2;
  ex=  3;
  ey=  2;
  cx= width/2;
  cy= height/2;
  sx = -50;
  mx = -50;
  state = 0;
  gravity = 0.1;
  for (int i = 0; i < xpos.length; i++){
    xpos [i] = random(0,width);
  } 
  for (int i = 0; i < a50.length; i++){
    a50 [i] = 70 - (i*.5);
  } 
  for (int i = 0; i < a210.length; i++){
    a210 [i] = 200 + (i*.5);
  } 
  for (int i = 0; i < shade.length; i++){
    shade [i] = 175 - (i*3);
  } 
  for (int i = 0; i < len.length; i++){
    len [i] = 60 + (i);
  } 
}

void draw() {
  drawMiscScenery();
   if (state == 0){
    drawSun();
  }
  if (state == 1){
    drawMoon();
  }
  drawForest(250);
  drawHouse(400,220);
  drawText();                                      
  drawEmblem(x,y);         
  moveEmblem();
  drawChaser(cx,cy);
  moveChaser();
  
  
}

void drawSun(){
  sy = -sqrt(pow(1000,2)-pow(sx-320, 2))+1000;
  sx= sx+1;
  fill( 255,255,0 );
  ellipse( sx, sy, 40,40 );  
  if (sx > width+50){
    state = 1;
    sx = -50;
  }
}

void drawMoon(){
  my = -sqrt(pow(1000,2)-pow(mx-320, 2))+1000;
  mx= mx+1;
  fill( 255,255,255 );
  ellipse( mx, my, 40,40 );  
  if (mx > width+50){
    state = 0;
    mx = -50;
  }
}

void drawMiscScenery(){
  background( 100,150,200 );                // sky
  fill( 100,200,100 );
  rect( 0,horizon, width,height*3/4 );      // grass.
}

void drawTree(float treeX, float treeY, float trans, float lenin, float a50, float a210){
  rectMode(CORNER);
  noStroke();
  fill(139,69,19);
  rect(treeX-5, treeY-a50, 10, lenin);
  fill(34,139,34);
  quad(treeX-50, treeY-a50, treeX-25, treeY-100, treeX+25, treeY-100, treeX+50, treeY-a50);
  quad(treeX-40, treeY-100, treeX-15, treeY-150, treeX+15, treeY-150, treeX+40, treeY-100);
  triangle(treeX-30, treeY-150, treeX+30, treeY-150, treeX, treeY-a210);
  noStroke();           //MASK
  fill(0,trans);
  rect(treeX-5, treeY-a50, 10, lenin);
  quad(treeX-50, treeY-a50, treeX-25, treeY-100, treeX+25, treeY-100, treeX+50, treeY-a50);
  quad(treeX-40, treeY-100, treeX-15, treeY-150, treeX+15, treeY-150, treeX+40, treeY-100);
  triangle(treeX-30, treeY-150, treeX+30, treeY-150, treeX, treeY-a210);
}
  
void drawForest(float y){
  for (int i = 0; i <xpos.length; i++) {                       
   drawTree(xpos[i], y, shade[i], len[i], a50[i], a210[i]);
  }
}
  
void drawHouse(float x, float y){
  fill(175);                                        
  rect(x,y,150,150);
  fill(150);
  triangle(x,y,x+150,y,x+75,y-50);
  fill(0);
  rect(x+65,y+110,20,40);
  fill(255,255,0);
  rect(x+30,y+40, 20,20);
  rect(x+100,y+40, 20,20);
}

void drawText(){
  fill(0);
  text( "My name is Nick", 10,height-20 );       
}

void drawEmblem (float x, float y) {
  fill(255,0,255);
  noStroke();
  rect(x,y,20,10);
  rect(x,y+9,10,21);
  rect(x+9,y+20,32,10);
  rect(x+40,y+9,10,21);
  rect(x+30,y,20,10);
  rect(x+10,y+29,10,11);
  rect(x+30,y+29,10,11);
  rect(x+20,y+39,10,10);
  fill(0);
  text( "Ferron", x,y );  
}

void moveEmblem(){
  x=  x + ex;                
  y=  y + ey;
  ey = ey + gravity;
  if ((x>width-50) || (x<0)){
    ex = ex * -.95;
  }
 if (y < 0) {
   ey = ey * -1;
 }
   if (y>height-50) {
    ey = ey * -0.95;
  }
}

void drawChaser(float x,float y){
  fill(255,0,0);
  rect(x,y,20,20);
}

void moveChaser(){
  cx = cx - (cx - x)/30;
  cy = cy - (cy - y)/40;
}
  
  
  

//////// HANDLERS:  mouse clicks, keys
void mousePressed() {
  x=  mouseX;                             // Set (x,y) to mouse
  y=  mouseY;
  //
  ex=  random( -20, +20 );                  // random speed.
  ey=  random( -14, +14 );
}

void keyPressed() {
  if (key == 'q') {
    exit();                           // press 'q' key to QUIT.
  }
}
   
   

