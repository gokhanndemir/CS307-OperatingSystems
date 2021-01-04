#include <stdio.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#pragma warning(suppress : 4996)

int main()
{
	int fd = open("loremipsum.txt", O_RDONLY);
	struct stat s;
	size_t size;
	int status = fstat(fd, &s);
	size = s.st_size;
	int counter = 0;
	char* ptr = mmap(0, size, PROT_READ, MAP_PRIVATE, fd, 0);
	if (ptr == MAP_FAILED)
	{
		printf("Mapping failed \n");
		return 1;
	}
	for (int i = 0; i < size; i++)
	{
		char c = ptr[i];
		if (c == 'a')
			counter++;
	}
	printf("Total count of 'a' is %i",counter);
	return 0;
}