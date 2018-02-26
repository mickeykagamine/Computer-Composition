`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:33:31 12/07/2016 
// Design Name: 
// Module Name:    hazard 
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
module hazard(
	input [4:0]WriteRegW,
	input [4:0]WriteRegM,
	input [4:0]WriteRegE,

   input RegWriteD,
	input RegWriteW,
	input RegWriteM,
	input [1:0]MemtoRegM,
	input RegWriteE,
	input [1:0]MemtoRegE,
	input BranchD,
	input Branch2D,
	input Branch3D,
	input [2:0]JD,
	
	input [4:0]rsE,
	input [4:0]rtE,
	input [4:0]rdE,
	input [4:0]rsD,
	input [4:0]rtD,
	
	
	output [1:0]ForwardBE,
	output [1:0]ForwardAE,
	output FlushE,
	output [1:0]ForwardBD,
	output [1:0]ForwardAD,
	output ForwardBM,
	output [1:0]ForwardJ,
	output StallD,
	output StallF
	
    );
	 wire lwstall,branchstall,lw2stall,jrstall,jistall;
	 
	assign ForwardAE=(((rsE!=0)&(rsE==WriteRegM)&RegWriteM)?2'b10
							:(((rsE!=0)&(rsE==WriteRegW)&RegWriteW)?2'b01
							:2'b00)),
			 ForwardBE=(((rtE!=0)&(rtE==WriteRegM)&RegWriteM)?2'b10
							:(((rtE!=0)&(rtE==WriteRegW)&RegWriteW)?2'b01
							:2'b00)),

			 lwstall=(((rsD==rtE)|(rtD==rtE))&(MemtoRegE==2'b01))
						|(((rsD==WriteRegM)|(rtD==WriteRegM))&(MemtoRegM==2'b01)),
			 lw2stall=(((rsD==rdE)|(rtD==rdE))&(MemtoRegE==2'b01))
						|(((rsD==WriteRegM)|(rtD==WriteRegM))&(MemtoRegM==2'b01)),
			 ForwardAD=(((rsD!=0)&(rsD==WriteRegE)&RegWriteE&(MemtoRegE!=2'b01))?2'b11
							:(((rsD!=0)&(rsD==WriteRegM)&RegWriteM)?2'b10
							:(((rsD!=0)&(rsD==WriteRegW)&RegWriteW)?2'b01
							:2'b00))),
			 ForwardBD=(((rtD!=0)&(rtD==WriteRegE)&RegWriteE&(MemtoRegE!=2'b01))?2'b11
				         :(((rtD!=0)&(rtD==WriteRegM)&RegWriteM)?2'b10
							:(((rtD!=0)&(rtD==WriteRegW)&RegWriteW)?2'b01
							:2'b00))),
			 ForwardBM=((WriteRegM!=0)&(WriteRegM==WriteRegW)&RegWriteW)?1:0,
			 ForwardJ=(((rsD!=0)&(rsD==WriteRegE)&RegWriteE&(MemtoRegE!=2'b01))?2'b11
				         :(((rsD!=0)&(rsD==WriteRegM)&RegWriteM)?2'b10
							:(((rsD!=0)&(rsD==WriteRegW)&RegWriteW)?2'b01
							:2'b00))),
			 branchstall=//((JD==2'b10)&RegWriteE&((WriteRegE==rsD)|(WriteRegE==rtD)))
							//(RegWriteE&((WriteRegE==rsD)|(WriteRegE==rtD)))
							((BranchD|Branch2D|Branch3D|JD==3'b100)&RegWriteE&((WriteRegE==rsD)|(WriteRegE==rtD)))
							||((BranchD|Branch2D|Branch3D|JD==3'b100)&(MemtoRegM==2'b01)&((WriteRegM==rsD)|(WriteRegM==rtD))),
												
			 
			 jrstall= ((JD==3'b010)&RegWriteE&(WriteRegE==rsD))
						  ||((JD==3'b010)&(MemtoRegM==2'b01)&(WriteRegM==rsD)),
		    jistall= ((JD==3'b101|JD==3'b100)&RegWriteE&(WriteRegE==rtD))
						  ||((JD==3'b101|JD==3'b100)&(MemtoRegM==2'b01)&(WriteRegM==rtD)),
			 
			 //bgtzstall
			 
  			 
	//		 stall=RegWriteD&((WriteRegE==rsD)|(WriteRegE==rtD)),
			 StallF=lwstall|branchstall|jrstall|lw2stall|jistall,
			 StallD=lwstall|branchstall|jrstall|lw2stall|jistall,
			 FlushE=lwstall|branchstall|jrstall|lw2stall|jistall;			

endmodule
