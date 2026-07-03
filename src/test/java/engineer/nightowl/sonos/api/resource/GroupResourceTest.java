package engineer.nightowl.sonos.api.resource;

import engineer.nightowl.sonos.api.domain.SonosGroupInfo;
import engineer.nightowl.sonos.api.domain.SonosGroups;
import engineer.nightowl.sonos.api.exception.SonosApiClientException;
import java.net.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GroupResourceTest extends MockedApiTestSetup
{
    private GroupResource resource;

    @BeforeEach
    void initResource()
    {
        resource = new GroupResource(client);
    }

    @Test
    void testGetGroups() throws Exception
    {
        stubJsonResponse(new SonosGroups());

        resource.getGroups("token123", "household1");

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/households/household1/groups", sent.uri().getPath());
    }

    @Test
    void testGetGroupsRejectsNullHouseholdId()
    {
        assertThrows(SonosApiClientException.class, () -> resource.getGroups("token123", null));
    }

    @Test
    void testCreateGroup() throws Exception
    {
        stubJsonResponse(new SonosGroupInfo());
        final List<String> playerIds = Collections.singletonList("player1");

        resource.createGroup("token123", "household1", playerIds, null);

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/households/household1/groups/createGroup", sent.uri().getPath());
    }

    @Test
    void testCreateGroupRejectsNullHouseholdId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.createGroup("token123", null, Collections.singletonList("player1"), null));
    }

    @Test
    void testCreateGroupRejectsNullPlayerIds()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.createGroup("token123", "household1", null, null));
    }

    @Test
    void testModifyGroupMembers() throws Exception
    {
        stubJsonResponse(new SonosGroupInfo());

        resource.modifyGroupMembers("token123", "group1", Collections.singletonList("player1"), null);

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/groups/group1/groups/modifyGroupMembers", sent.uri().getPath());
    }

    @Test
    void testModifyGroupMembersRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.modifyGroupMembers("token123", null, Collections.singletonList("player1"), null));
    }

    @Test
    void testModifyGroupMembersRejectsBothListsEmpty()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.modifyGroupMembers("token123", "group1", null, null));
    }

    @Test
    void testSetGroupMembers() throws Exception
    {
        stubJsonResponse(new SonosGroupInfo());

        resource.setGroupMembers("token123", "group1", Collections.singletonList("player1"));

        final HttpRequest sent = captureRequest();
        assertEquals("/control/api/v1/groups/group1/groups/setGroupMembers", sent.uri().getPath());
    }

    @Test
    void testSetGroupMembersRejectsNullGroupId()
    {
        assertThrows(SonosApiClientException.class,
                () -> resource.setGroupMembers("token123", null, Collections.singletonList("player1")));
    }
}
