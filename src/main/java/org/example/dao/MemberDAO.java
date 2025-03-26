package org.example.dao;

import org.example.dto.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    public List<Member> members;

    public MemberDAO() {
        members = new ArrayList<Member>();
    }
}
