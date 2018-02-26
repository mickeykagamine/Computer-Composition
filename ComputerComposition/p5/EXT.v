`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    17:55:20 12/08/2016 
// Design Name: 
// Module Name:    EXT 
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
module EXT(
	input [31:0]Instr,
	output [31:0]SignImmD
    );
	wire r,addu,subu,ori,lw,sw,beq,lui,nop,jialc,ji;
	assign r=(Instr[31:26]==6'b000000)?1:0,
			ori=(Instr[31:26]==6'b001101)?1:0,
			lw=(Instr[31:26]==6'b100011)?1:0,
			lwe=(Instr[31:26]==6'b011111)?1:0,
			sw=(Instr[31:26]==6'b101011)?1:0,
			beq=(Instr[31:26]==6'b000100)?1:0,
			lui=(Instr[31:26]==6'b001111)?1:0,
			addi=(Instr[31:26]==6'b001001|Instr[31:26]==6'b001000)?1:0,
			jialc=((Instr[31:26]==6'b111110)?1:0),
			ji=((Instr[31:26]==6'b110110)?1:0);

	assign addu=(((r==1)&&(Instr[5:0]==6'b100001))?1:0),
			subu=(((r==1)&&(Instr[5:0]==6'b100011))?1:0);
	assign SignImmD=((ji||jialc|lw|sw|addi)?{{16{Instr[15]}},Instr[15:0]}:(lwe?{{23{Instr[15]}},Instr[15:7]}:{16'b0,Instr[15:0]}));		

endmodule
