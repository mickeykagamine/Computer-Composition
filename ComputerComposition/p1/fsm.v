`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    10:16:32 10/26/2016 
// Design Name: 
// Module Name:    FSM 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module fsm(
    input Clk,
    input Clr,
    input X,
    output  Z
 //  output  [1:0]state1
    );	
parameter S0=2'b00,S1=2'b01,S2=2'b10,S3=2'b11;
reg [1:0]state;
//reg Z;
//assign state1=state;
//assign Z1=Z;
	always@(posedge Clk or posedge Clr)begin
		if(Clr)
			state <= S0;
		else begin
			case(state)
				S0:if(X==1'b1)state<=S1;else state<=S0;
				S1:if(X==1'b0)state<=S2;else state<=S1;
				S2:if(X==1'b1)state<=S3;else state<=S0;
				S3:if(X==1'b1)state<=S1;else state<=S2;//可重复
			//	S3：if(X=='b1)state<=S1;else state<=S0;//不可重复
		//		default:state=S0;
			endcase
		//	$display ("%d     ",state);
			end
	end
/*
	always@(state)begin
		case(state)
			S0:Z<=1'b0;
			S1:Z<=1'b0;
			S2:if(X==1'b1) Z<='b1;
				else Z<=1'b0;
			S3:Z<=1'b0;
	//		default:Z=1'b0;
		endcase
	end*/
	//wire Z2;
	assign Z=(state==3)?1:0;
	initial begin
	 state=0;
	end
endmodule
///////////////////////////////////////////////////////////////////////////////////////////////////////
/*fsm101test
module test_fsm_2;

	// Inputs
	reg Clk;
	reg Clr;
	reg X;

	// Outputs
	wire Z;

	// Instantiate the Unit Under Test (UUT)
	fsm uut (
		.Clk(Clk), 
		.Clr(Clr), 
		.X(X), 
		.Z(Z)
	);

	initial begin
		// Initialize Inputs
		Clk = 0;
		Clr = 0;
		X = 0;
		#10 X=1;
		#10 X=0;
		#10 X=1;
		#10 X=0;
		#10 X=1;
		#10 X=0;


		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here

	end
   always #5 Clk=~Clk;
endmodule

*/











///////////////////////////////////////////////////////////////////////////////////////////////////////
/*fsm101101
module fsm101101(
    input Clk,
    input Clr,
    input X,
    output Z
    );

parameter S0=3'b000,S1=3'b001,S2=3'b010,S3=3'b011,S4=3'b100,S5=3'b101,S6=3'b110;
reg [2:0]state;
//reg Z;
//assign state1=state;
//assign Z1=Z;
	always@(posedge Clk or posedge Clr)begin
		if(Clr)
			state <= S0;
		else begin
			case(state)
				S0:if(X==1'b1)state<=S1;else state<=S0;
				S1:if(X==1'b0)state<=S2;else state<=S1;
				S2:if(X==1'b1)state<=S3;else state<=S0;
				S3:if(X==1'b1)state<=S4;else state<=S2;
				S4:if(X==1'b1)state<=S1;else state<=S5;
				S5:if(X==1'b1)state<=S6;else state<=S0;
			//	S6:if(X==1'b1)state<=S1;else state<=S2;//可重复
			 S6:if(X==1'b1)state<=S1;else state<=S0;//不可重复
				default:state=S0;
			endcase
		//	$display ("%d     ",state);
		end
	end
	//wire Z2;
	assign Z=(state==6)?1:0;
	initial begin
	 state=0;
	end


endmodule
*/

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*fsm101101test
module shemegui;

	// Inputs
	reg Clk;
	reg Clr;
	reg X;

	// Outputs
	wire Z;

	// Instantiate the Unit Under Test (UUT)
	fsm101101 uut (
		.Clk(Clk), 
		.Clr(Clr), 
		.X(X), 
		.Z(Z)
	);

	initial begin
		// Initialize Inputs
		Clk = 0;
		Clr = 0;
		X = 0;
		#10
		X=1;
		#10
		X=0;
		#10
		X=1;
		#10
		X=1;
		#10
		X=0;
		#10
		X=1;

		#10
		X=1;
		#10
		X=0;
		#10
		X=1;
		#10
		X=1;
		#10
		X=0;
		#10
		X=1;

		

		#10
		X=0;
		#10
		X=1;
		#10
		X=1;
		#10
		X=0;
		#10
		X=1;

		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here

	end
   always #5 Clk=~Clk;
endmodule

*/













///////////////////////////////////////////////////////////////////////////////////////////
/*fsm16101
module fsm16101(
   input Clk,
   input Clr,
   input [15:0]X,
   output  Z,
   output  [1:0]state1,
	output z,
	output x1,
	output tmp1
    );	
	 
parameter S0=2'b00,S1=2'b01,S2=2'b10,S3=2'b11;
reg [1:0]state;
//reg Z;
assign state1=state;

//assign Z1=Z;
integer tmp;
assign tmp1=tmp;
integer i;
reg x;
assign x1=x;
reg z;
wire x0,x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,x14,x15;
assign {x0,x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,x14,x15}=X;
initial begin
	state=S0;
end


assign {x0,x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,x14,x15}=X&tmp;
	always@(posedge Clk or posedge Clr)begin
		if(Clr)
			state <= S0;	
		else begin
			for(i=0;i<16;i=i+1)begin				
				x=x0&x1&x2&x3&x4&x5&x6&x7&x8&x9&x10&x11&x12&x13&x14&x15;
			//	x<=X[i];
				case(state)
					S0:if(x==1'b1)state<=S1;else state<=S0;
					S1:if(x==1'b0)state<=S2;else state<=S1;
					S2:if(x==1'b1)state<=S3;else state<=S0;
					S3:if(x==1'b1)state<=S1;else state<=S2;//可重复使用
				//	S3：if(x=='b1)state<=S1;else state<=S0;//不可重复
					default:state=S0;
				endcase
				
				if(state==3)begin
					z=z+1;
				end
				tmp=tmp*2;
			end
		
		//	$display ("%d     ",state);
			end
	end
	
	assign Z=z;
	initial begin
		state<=0;
	   tmp =1;
		z=0;
	end
	
endmodule
*/
////////////////////////////////////////////////////////////////////////////////////////////////////////
/*16-101 test
module test16101;

	// Inputs
	reg Clk;
	reg Clr;
	reg [15:0] X;
	wire [1:0]state1;
	// Outputs
	wire Z;
	wire z;
	wire x1;
	wire tmp1;
	// Instantiate the Unit Under Test (UUT)
	fsm16101 uut (
		.Clk(Clk), 
		.Clr(Clr), 
		.X(X), 
		.Z(Z),
		.z(z),
		.state1(state1),
		.x1(x1),
		.tmp1(tmp1)
	);

	initial begin
		// Initialize Inputs
		Clk = 0;
		Clr = 0;
		X = 0;
	//	state1=0;
		//z=0;
		// Wait 100 ns for global reset to finish
		#10 X=16'b1010101010101010;
        #100;
		// Add stimulus here

	end
   always #10 Clk=~Clk;
endmodule

*/