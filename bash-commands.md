---
layout: default
img: tar
caption: Life skills
title: Bash Cheat Sheet
active_tab: 
release_date: 
due_date: 
---

<!-- Check whether the assignment is up to date -->
{% capture this_year %}{{'now' | date: '%Y'}}{% endcapture %}
{% capture due_year %}{{page.due_date | date: '%Y'}}{% endcapture %}
{% if this_year != due_year %} 
{% endif %}
<!-- End of check whether the assignment is up to date -->


Bash<span class="text-muted">: Greatest Hits</span> 
=============================================================

#### Where am I??
{% highlight tcsh %}
$ ls # list all the files in this directory
$ ls some/directory # list all the files in some/directory
$ pwd # print the directory where you are currently working ("print working directory")
$ cd some/directory # go to some/directory ("change directory")
$ cd ~/ # take me home ("change directories to the user's home directory")
$ mkdir new_directory # create a new diretory called new_directory ("make directory")
$ rm file.txt # remove file.txt (be careful, it will be gone for good).
$ rm -r directory # remove directory and all the files within it (again, gone for good).
{% endhighlight %}

#### What the $#@! are these files?
{% highlight tcsh %}
$ ls -lah file.txt #print the size of file.txt in bytes (also the date it was created)
$ wc -l file.txt #print the number of lines in file.txt
$ file file.txt #print the type of the file (e.g. is it a text file? compressed archive?)
$ head file.txt #print the first 10 lines of file.txt
$ head -NUM file.txt #print the first NUM lines of file.txt (e.g. head -3 prints first 3 lines)
$ tail -NUM file.txt #print the last NUM lines of file.txt (e.g. tail -3 prints last 3 lines)
$ cat file.txt #print the entire contents of file.txt
$ tar -xzvf file.tar.gz #unzip a tar archived file
$ tar -czvf files.tar.gz files/ #create a tar archived file containing the contents of the directory files/
{% endhighlight %}

#### Exploring and reorganizing file contents
{% highlight tcsh %}
$ sort file.txt # sort the lines in file.txt (by default, alphabetically and ascending)
$ sort -r file.txt # sort the lines in file.txt in reverse order
$ sort -nr file.txt # sort the lines in file.txt numerically and in reverse order
$ uniq file.txt # remove duplicate lines (only works if you use "sort" first)
$ uniq -c file.txt # print out unique lines and the number of times each one occurs
$ cut -f 1 file.txt # print the first column of the file.txt (assumes columns are tab-separated)
$ cut -f 1 -d ',' file.csv # print the first column of file.csv, split on comma instead of tab
$ grep "phrase" file.txt # print out lines in file.txt that contain the string "phrase"
$ grep -i "phrase" file.txt # same as above, but ignoring case
$ grep -v "phrase" file.txt # print out lines in file.txt that don't contain the string "phrase"
$ shuf file.txt # shuffle the lines in file.txt
{% endhighlight %}

#### Gettin' fancy
Bash commands are connecting via "pipes", which means the input of one command is the output of the previous command. We use the | character to "pipe" one command's output into another. Below are some useful examples.
{% highlight tcsh %}
$ cat file.txt | sort | uniq | wc -l # how many unique lines are in this file
$ cat file.txt | sort | uniq -c | sort -nr # print out the unique lines in file.txt, with the most frequent line and its count at the top
$ cat file.txt | grep "phrase" | wc -l # how many lines in this file contain the phrase "phrase" 
$ cat file.txt | sort | uniq -c | sort -nr | head -100 | shuf | head -10 # take a random sample of 10 of the top 100 most frequent lines
{% endhighlight %}
Bash can also write to files! This makes a life a lot easier than dragging your cursor, copying large chunks of text from the terminal window. File Output Redirection can be done using the <code> > </code> or <code> >> </code> operators. 
{% highlight tcsh %}
$ grep "Hello" file.txt > output.txt # Stores every line containing "Hello" in file.txt in a new file output.txt
$ cat file.txt | sort | uniq -c | sort -nr > output.txt # stores the unique lines of file.txt, with the most frequent line and its count at the top, in output.txt. Overwrites the old contents of output.txt (Careful!)
$ cat output.txt | wc -l >> output.txt # Appends the line count of output.txt to the end of output.txt
{% endhighlight %}

#### Downloading Files
To download the contents of any URL (whether it be a file, HTML page or even a picture), there's a very useful command on Mac and Linux.
{% highlight tcsh %}
$ wget https://cs.brynmawr.edu/cs113/website/hws/HW00.html # Downloads the contents of the URL as the file 'HW00.html' to the current directory (used in Linux)
{% endhighlight %}
{% highlight tcsh %}
$ curl -O https://cs.brynmawr.edu/cs113/website/hws/HW00.html # Downloads the contents of the URL as the file 'HW00.html' to the current directory (used in Mac)
{% endhighlight %}

#### Uploading Files and Directories
To turn in your HWs, you will need to upload files to Gradescope. If you are accessing the CS lab machines remotely, you will need to copy the files over onto your own computer to upload to Gradescope. 
There are some commands below which show how to transfer your HW files from the lab machine to your local computer over SSH. The <code>:~/</code> at the end is required always. For directories, the <code>-r</code> flag is required. 
{% highlight tcsh %}
$ scp  username@serveraddress:/home/username/path-to-file/your_file_name . # This copies the file "your_file_name" from the remote server to your computer. 
$ scp johndoe@goldengate.cs.brynmawr.edu:/home/johndoe/CS113/hw10/README.txt . # This copies README.txt to your current directory. 
$ scp -r username@serveraddress:~/home/username/path-to-dir . # This copies the entire directory "your_dir" from the remote server to your current directory on your own computer.
$ scp -r johndoe@goldengate.cs.brynmawr.edu:~/home/username/CS113/hw09 # This copies the hw09 directory from the remote server to your computer.
{% endhighlight %}

#### VIM
We will be using vim/vi as our text editor. vim is a powerful editor. Make sure to do the vim tutorial linked to in the first lab. 
Here are some of the most common things you need to remember about vim

This [cheat sheet](https://vim.rtorr.com/) lists lots of useful vim commands on one page.

{% highlight tcsh %}
`i` starts an interactive session
`ESC` will end interactive mode
`:` is how you specific many command
`:w` writes/saves the file
`:q` quits vim
`:q!` quits vim without saving changes
`:! <...>` will run a bash command. For example `:! ls` will list the files in the directory you are currently in
{% endhighlight %}

