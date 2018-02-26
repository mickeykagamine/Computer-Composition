`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    10:57:19 12/07/2016 
// Design Name: 
// Module Name:    IDEX 
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
module IDEX(
input [31:0]InstrD,
output [31:0]InstrE,
	input [2:0]JD,
	output [2:0]JE,
	input [31:0]PCPlus8D,
	
	input [31:0]RD1D,
	input [31:0]RD2D,
	input [31:0]SignImmD,
	input [4:0]RsD,
	input [4:0]RtD,
	input [4:0]RdD,
	input [4:0]SD,
	
	input Clk,
	input Reset,
	input FlushE,
	
	input RegWriteD,
	input [1:0]MemtoRegD,
	input MemWriteD,
	input [5:0]ALUoperationD,
	input [1:0]ALUSrcD,	
	input [1:0]REGDstD,

	output [31:0]PCPlus8E,

	output [31:0]RD1E,
	output [31:0]RD2E,
	output [31:0]SignImmE,
	output [4:0]RsE,
	output [4:0]RtE,
	output [4:0]RdE,
	output [4:0]SE,

	output [1:0]REGDstE,
	output [1:0]ALUSrcE,
	output RegWriteE,
	output [5:0]ALUoperationE,
	output MemWriteE,
	output [1:0]MemtoRegE

    );
	 initial begin
			rd1<=32'b0;
			rd2<=32'b0;
			extend<=32'b0;
			rs<=5'b0;
			rt<=5'b0;
			rd<=5'b0;
			s<=5'b0;
			j<=3'b0;			
			pcplus8<=32'b0;
		instr<=32'b0;
			regdst<=2'b00;
			regwrite<=0;
			memwrite<=0;
			memtoreg<=0;
			alusrc<=3'b0;
			aluoperation<=3'b0;
	 end
 reg [31:0]rd1,rd2,extend,instr;
	 reg [4:0]rs,rt,rd,s;
	 reg [1:0]regdst;
	 reg regwrite,memwrite,w,bgezal;
	 reg [1:0]memtoreg;
	 reg [2:0]alusrc;
	 reg [5:0]aluoperation;
	 reg [2:0]j;
	 reg [31:0]pcplus8;
	 assign  RD1E=rd1,
				RD2E=rd2,
				SignImmE=extend,
				RsE=rs,
				RtE=rt,
				RdE=rd,
				SE=s,
				JE=j,				
				PCPlus8E=pcplus8,
			InstrE=instr,
				REGDstE=regdst,
				RegWriteE=regwrite,
			   MemWriteE=memwrite,
	 		   MemtoRegE=memtoreg,
			   ALUSrcE=alusrc,
			   ALUoperationE=aluoperation;
//	assign RdE[0]=rd[0];
				always @(posedge Clk)begin
		if(Reset||FlushE)begin
			rd1<=32'b0;
			rd2<=32'b0;
			extend<=32'b0;
			rs<=5'b0;
			rt<=5'b0;
			rd<=5'b0;
			s<=5'b0;
			j<=3'b0;			
		instr<=32'b0;
			regdst<=2'b0;
			regwrite<=0;
			memwrite<=0;
			memtoreg<=0;
			alusrc<=3'b0;
			aluoperation<=3'b0;
		end
		else if(Reset)begin
			pcplus8<=31'b0;
		end
		else begin
			rd1<=RD1D;
			rd2<=RD2D;
			extend<=SignImmD;
			rs<=RsD;
			rt<=RtD;
			rd<=RdD;
			s<=SD;
			j<=JD;
			pcplus8<=PCPlus8D;
		instr<=InstrD;
			regdst<=REGDstD;
			regwrite<=RegWriteD;
			memwrite<=MemWriteD;
			memtoreg<=MemtoRegD;
			alusrc<=ALUSrcD;
			aluoperation<=ALUoperationD;
		end
	 end


endmodule
