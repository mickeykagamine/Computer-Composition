`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:47:20 11/30/2016 
// Design Name: 
// Module Name:    controller 
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
module controller(
	input [5:0]A,
	input [5:0]B,
	input [4:0]rt,
	output REGDst,
	output [2:0]ALUSrc,
	output RegWrite,
	output [2:0]ALUoperation,
	output MemWrite,
	output MemRead,
	output MemtoReg,
	output W,
	output [1:0]J,
	output Branch,
	output Branch2,
	output bgezal
    );
	wire r,addu,subu,ori,lw,sw,beq,lui,nop,jal,jr,begz;
	assign r=(A==6'b000000)?1:0,
			ori=(A==6'b001101)?1:0,
			lw=(A==6'b100011)?1:0,
			sw=(A==6'b101011)?1:0,
			beq=(A==6'b000100)?1:0,
			lui=(A==6'b001111)?1:0,
			jal=(A==6'b000011)?1:0,
			bgezal=(A==6'b000001&rt==6'b10001)?1:0,
			bgez=(A==6'b000001&rt==6'b00001)?1:0;
	
	assign addu=(((r==1)&&(B==6'b100001))?1:0),
			subu=(((r==1)&&(B==6'b100011))?1:0),
			jr=((r==1)&&(B==6'b001000)?1:0),
			srlv=((r==1)&(B==6'b000110)?1:0);
			
	assign REGDst=r,
			ALUSrc=(ori?2'b10:((lw||sw)?2'b01:2'b00)),
			RegWrite=ori||lw||lui||addu||subu||srlv,
			ALUoperation=(addu||sw||lw||jr||jal?3'b000:(subu||beq||bgezal||bgez?3'b001:(ori?3'b011:(srlv?3'b100:3'b010)))),
			MemWrite=sw,
			MemRead=lw,
			MemtoReg=lw,
			W=lui,
			J=((jal==1)?01:((jr==1)?10:00)),
			Branch=beq,
			Branch2=bgezal||bgez;
endmodule
