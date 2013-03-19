/*
Copyright (C) 2007 graham sanderson

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/
package org.jpsx.runtime.components.emulator.disassemblers;

import org.jpsx.api.components.core.cpu.CPUInstruction;
import org.jpsx.api.components.core.cpu.CPUInstructionDisassembler;
import org.jpsx.api.components.core.cpu.InstructionProvider;
import org.jpsx.api.components.core.cpu.InstructionRegistrar;
import org.jpsx.runtime.JPSXComponent;
import org.jpsx.runtime.components.core.CoreComponentConnections;

public abstract class DisassemblerComponent extends JPSXComponent implements InstructionProvider {
    protected DisassemblerComponent(String description) {
        super(description);
    }

    @Override
    public void init() {
        super.init();
        CoreComponentConnections.INSTRUCTION_PROVIDERS.add(this);
    }

    protected static String padString(String s) {
        return padString(s, 8);
    }

    protected static String padString(String val, int chars) {
        String rc = val + "                                                ";
        return rc.substring(0, chars);
    }

    protected static class DIS implements CPUInstructionDisassembler {
        public String disassemble(CPUInstruction inst, int address, int ci) {
            return padString(inst.getName());
        }
    }

    public abstract void addInstructions(InstructionRegistrar registrar);
}
